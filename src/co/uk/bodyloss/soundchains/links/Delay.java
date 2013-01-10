package co.uk.bodyloss.soundchains.links;

import co.uk.bodyloss.soundchains.ChainLink;

public class Delay extends ChainLink {
	private float decay = 0.0F;
	private float delay = 0.0F;
	
	// used as a ring buffer
	private float[] buffer;
	private int tail = 0, head = 0;
	private boolean playingBack = false;
	
	/**
	 * Delays the sample by a an amount in milli-seconds then plays it back
	 * @param amount
	 */
	public Delay(float decay, float delay) {
		this.decay = decay;
		this.delay = delay;	
	}
	
	public void instantiate(int sampleRate) {
		super.instantiate(sampleRate);
		
		int numSamples = (int) Math.ceil((sampleRate / 1000) * this.delay);
		buffer = new float[numSamples];
		for (int i = 0; i < numSamples; i++)
			buffer[i] = 0.0f;
	}

	public float processSample(float sample, long sampleNum) {
		// rotate our ring buffer and grab the next value off of it
		buffer[head] = sample + (buffer[head++] * this.decay);
		head = head % buffer.length;
		float newSample  = 0.0f;
		if (playingBack || head == 0) {
			playingBack = true;
			newSample = buffer[tail++];
			tail = tail % buffer.length;
		}
		
		return sample + (newSample * this.decay);
	}
}
