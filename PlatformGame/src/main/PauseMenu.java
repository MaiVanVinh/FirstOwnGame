package main;


import java.awt.Color;
import java.awt.Dimension;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.formdev.flatlaf.FlatDarkLaf;

import Character.Player;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PauseMenu extends JPanel {


	private static final long serialVersionUID = 1L;
	private JButton restart;
	private JButton continueButton;
	private JLabel gamePause;
	private JLabel music;
	private JLabel sound;
	private JSlider sliderMusic;
	private JSlider sliderSound;
	private JButton nextSong;
	



	public PauseMenu() {
		
		 try {
			UIManager.setLookAndFeel(new FlatDarkLaf());
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//setBorder(new EmptyBorder(5, 5, 5, 5));
		setPreferredSize(new Dimension(500, 500));
		setBackground(Color.DARK_GRAY);
		setLayout(null);
		
		restart = new JButton("Restart");
		restart.setBounds(62, 375, 132, 56);
		restart.setFocusable(false);
		restart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainGame.restart = true;
	  			if(!MainGame.pause)
     				MainGame.pause = true;
     			else
     				MainGame.pause = false;
	  			MainGame.resetSong = true;
				setVisible(false);
			}});
		add(restart);
		
		continueButton = new JButton("Continue");
		continueButton.setBounds(285, 375, 132, 56);
		continueButton.setFocusable(false);
		continueButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
     			if(!MainGame.pause)
     				MainGame.pause = true;
     			else
     				MainGame.pause = false;
				setVisible(false);
				
			}
		});
		add(continueButton);
		
		gamePause = new JLabel("Game Paused");
		gamePause.setFont(new Font("Tahoma", Font.PLAIN, 30));
		gamePause.setBounds(153, 11, 213, 43);
		add(gamePause);
		
		music = new JLabel("Music");
		music.setFont(new Font("Tahoma", Font.PLAIN, 20));
		music.setBounds(37, 116, 62, 23);
		add(music);
		
		sound = new JLabel("Sound");
		sound.setFont(new Font("Tahoma", Font.PLAIN, 20));
		sound.setBounds(37, 248, 62, 23);
		add(sound);
		
		sliderMusic = new JSlider(-60,6,-23);
		sliderMusic.setBounds(135, 116, 200, 26);
		sliderMusic.setFocusable(false);
		sliderMusic.setValue(-23);
		sliderMusic.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				changeVolumeMusic();
			}});
		add(sliderMusic);
		
		sliderSound = new JSlider(-60,6,-23);
		sliderSound.setBounds(135, 248, 200, 26);
		sliderSound.setFocusable(false);
		sliderSound.setValue(-40);
		sliderSound.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				changeVolumeMusic();			
			}});
		add(sliderSound);
		
		nextSong = new JButton("Next Song");
		nextSong.setBounds(374, 119, 100, 33);
		nextSong.setFocusable(false);
		nextSong.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nextSong();			
			}});
		add(nextSong);
		
	}


	private void changeVolumeMusic() {
		MainGame.ChangeVolume = sliderMusic.getValue();
		Player.ChangeVolume = sliderSound.getValue();
	}
	
	private void nextSong() {
		MainGame.nextSong = true;
	}
}