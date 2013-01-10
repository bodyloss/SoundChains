package co.uk.bodyloss.soundchains;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;


public class SoundChain {
	//The rate at which we expand our array of links
	private final double EXPANSION_FACTOR = 1.5;
	
	private int sampleRate = 44100;
	private Generator generator = null;
	private ChainLink[] links = new ChainLink[2];
	private int index = 0;
	
	public SoundChain(int sampleRate) {
		this.sampleRate = sampleRate;		
	}
	
	/**
	 * Sets the generator that is used to generate samples for the chain
	 * @param generator
	 * @return The chain
	 */
	public SoundChain setGenerator(Generator generator) {
		this.generator = generator;
		this.generator.setSampleRate(this.sampleRate);
		return this;
	}
	
	/**
	 * Add a link in the sound chain which will process a single
	 * sample and pass it on
	 * @param link The link
	 * @return The Chain
	 */
	public SoundChain addLink(ChainLink link) {
		// check whether we have to expand our links array
		if (index == this.links.length) {
			ChainLink[] temp = links;
			this.links = new ChainLink[(int) ((double)links.length * EXPANSION_FACTOR)];
			System.arraycopy(temp, 0, this.links, 0, temp.length);
		}
		link.instantiate(this.sampleRate);
		this.links[index++] = link;
		return this;
	}
	
	public void playChain(float seconds) throws Exception {
		this.playChain((int) seconds * this.sampleRate);
	}
	
	/**
	 * Plays the audio chain for the given number of samples
	 * @param numSamples
	 * @throws Exception 
	 */
	public void playChain(int numSamples) throws Exception {
		if (numSamples % this.sampleRate != 0)
			throw new Exception("Fuck of, only exact seconds allwed");
		
		AudioFormat audioFormat = new AudioFormat(
				(float)this.sampleRate,
				16, // sample size in bits (floats)
				2, // 2 channels
				true, // signed PCM
				false //little endian
			);
			
		SourceDataLine line = null;
		DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioFormat); 
		
		try {
			line = (SourceDataLine) AudioSystem.getLine(info);
			line.open(audioFormat);
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		line.start();
		
		// run our chain, buffering and writing out to audio as we go
		byte[] buffer = new byte[this.sampleRate * 4];
		for (int second = 0; second < numSamples / this.sampleRate; second++) {
			int sampleIncreaseOffset = second * this.sampleRate;
			
			// generate one whole seconds worth of samples
			for (int sampleNum = 0; sampleNum < this.sampleRate; sampleNum++) {
				long actualSample = sampleNum + sampleIncreaseOffset;
				
				float sample = this.generator.getNextSample(actualSample);
				for (int j = 0; j < this.index; j++) {
					sample = this.links[j].processSample(sample, actualSample);
				}
				
				int baseAddr = sampleNum * 4;
				int value = (int) (sample * 30000.0f);
				// little endian encoding to bytes
				buffer[baseAddr + 0] = (byte) (value & 0xFF);
				buffer[baseAddr + 1] = (byte) ((value >>> 8) & 0xFF);
				buffer[baseAddr + 2] = (byte) (value & 0xFF);
				buffer[baseAddr + 3] = (byte) ((value >>> 8) & 0xFF);
			}
			
			line.write(buffer, 0, buffer.length);
		}
		line.drain();
	}
}

























