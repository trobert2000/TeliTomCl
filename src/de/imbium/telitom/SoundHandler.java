package de.imbium.telitom;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.*;
import java.net.*;

public class SoundHandler extends Thread {
	
	final int BUF_SIZE = 1400;
	private AudioFormat format; 
	private TargetDataLine line;
	private Socket s;
	
	public SoundHandler() {
		format = new AudioFormat(11025,16,1,true,true);
	}
	
	
	public void run() {
		startRecording();
		try {
			DataInputStream in = new DataInputStream(s.getInputStream()) ;
			DataOutputStream out = new DataOutputStream(s.getOutputStream()); 
			byte[] buf = new byte[1400];
			byte[] buf2 = new byte[1400];
			boolean runFlag=true;
			int cnt=0;
			while(runFlag && cnt++ < 10) {// && (in.read(buf) > -1)) {
				
				// read audio
				int bytesRead = line.read(buf, 0, BUF_SIZE);
				if(bytesRead > 0) {
					// write data to socket
					out.write(buf);
					sleep(100);
					// read data from socket
					bytesRead = in.read(buf2);
					if(bytesRead> 0) {
						// play audio
						
					}
				}
				
				
				sleep(100);
			}
			stopRecording();
			in.close();
			out.close();
			s.close();
			
			
		//} catch(LineUnavailableException e3) {
		//	e3.printStackTrace();
		} catch(InterruptedException | IOException e1) {
			e1.printStackTrace();
		}
		
	}
	
	public void startRecording() {

		// path of the wav file
		String fnam = System.getProperty("user.dir")+ File.separator+"helpfiles"+File.separator+"RecordAudio.wav";
		File wavFile = new File(fnam);

		// format of audio file
		AudioFileFormat.Type fileType = AudioFileFormat.Type.WAVE;


		DataLine.Info info = new DataLine.Info(TargetDataLine.class, 
				format); // format is an AudioFormat object
		
		try {
				
			line = (TargetDataLine) AudioSystem.getLine(info);
			line.open(format);

			//AudioInputStream ais = new AudioInputStream(line);

			System.out.println("Start Recording...");
			line.start();
	
		} catch (LineUnavailableException ex) {// | IOException ex) {
			// Handle the error ... 
			ex.printStackTrace();
		}
	}

	public void stopRecording() {
		line.stop();
		line.close();		
		System.out.println("Stop Recording");
	}
	
	public void startPlaying() {
	
	}

	public void stopPlaying() {
		line.stop();
		line.close();		
		System.out.println("Stop Recording");
	}
	
	public void play(String audioFilePath) {
		File audioFile = new File(audioFilePath);
		try {
			AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);

			AudioFormat format = audioStream.getFormat();

			DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);

			SourceDataLine audioLine = (SourceDataLine) AudioSystem.getLine(info);

			audioLine.open(format);

			audioLine.start();
			
			System.out.println("Playback started.");
			
			byte[] bytesBuffer = new byte[1024];
			int bytesRead = -1;

			while ((bytesRead = audioStream.read(bytesBuffer)) != -1) {
				audioLine.write(bytesBuffer, 0, bytesRead);
			}
			
			audioLine.drain();
			audioLine.close();
			audioStream.close();
			
			System.out.println("Playback completed.");
			
		} catch (UnsupportedAudioFileException ex) {
			System.out.println("The specified audio file is not supported.");
			ex.printStackTrace();
		} catch (LineUnavailableException ex) {
			System.out.println("Audio line for playing back is unavailable.");
			ex.printStackTrace();
		} catch (IOException ex) {
			System.out.println("Error playing the audio file.");
			ex.printStackTrace();
		}		
	}

}
