package NiniMuzaAplk;

import java.util.Scanner;

import javax.sound.midi.MidiEvent;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Track;

public class MiniMuazAplkWrsPlc {
	public static void main (String [] args) {
		
		MiniMuazAplkWrsPlc mini = new MiniMuazAplkWrsPlc();
		
		Scanner sc = new Scanner (System.in);
		boolean end=false;
		while (!end) {
			int instrument = sc.nextInt();
			int nuta = sc.nextInt();
			mini.graj(instrument, nuta);
			String isEnd =sc.nextLine();
			if(!isEnd.isEmpty()) 
				end=true;
		}
		sc.close();
		return;		
	}
	public void graj (int instrument, int nuta) {
		try {
			
			Sequencer sekwenser = MidiSystem.getSequencer();
			sekwenser.open();
			
			Sequence sekw = new Sequence (Sequence.PPQ, 4);
			Track sciezka = sekw.createTrack();
			
			//MidiEvent zdarzenie = null;
			
			ShortMessage pierwszy = new ShortMessage();
			pierwszy.setMessage(192,1,instrument,0);
			
			MidiEvent zmienInstrument = new MidiEvent(pierwszy, 1);
			sciezka.add(zmienInstrument);
			
			ShortMessage a = new ShortMessage();
			a.setMessage(144,1, nuta, 100);
			MidiEvent nutaP = new MidiEvent(a, 1);
			sciezka.add(nutaP);
			
			ShortMessage b = new ShortMessage();
			b.setMessage(128,1,nuta,100);
			MidiEvent nutaK = new MidiEvent(b, 16);
			sciezka.add(nutaK);
			sekwenser.setSequence(sekw);
			sekwenser.start();
			//sekwenser.wait(13516546);
			//sekwenser.stop();
			//sekwenser.close();
		} catch (Exception ex) { ex.printStackTrace();}
	}

}
