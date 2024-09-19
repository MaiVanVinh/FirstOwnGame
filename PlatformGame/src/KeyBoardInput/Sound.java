package KeyBoardInput;

import java.io.IOException;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Sound {

	Clip clip;
	FloatControl fc;
	public URL soundURL[] = new URL[30];
	
	public Sound() {
		soundURL[0] = getClass().getResource("/jump.wav");
		soundURL[1] = getClass().getResource("/Pitiful face.wav");
		soundURL[2] = getClass().getResource("/ThienLyOi.wav");

	}
	
	public void getSound(int i,int volume) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
     	    AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundURL[i]);
     	    clip = AudioSystem.getClip();
     	    clip.open(audioStream); 
            fc = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            fc.setValue(volume);
     	    clip.start();

		
	}
	
	public boolean checkActive() {
		if(clip.isActive()) return true;
		
		return false;
	}
	


}
