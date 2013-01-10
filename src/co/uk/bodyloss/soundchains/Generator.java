package co.uk.bodyloss.soundchains;

public abstract class Generator {
	protected int sampleRate = 44100;
	
	void setSampleRate(int sampleRate) {
		this.sampleRate = sampleRate;
	}
	
	public abstract float getNextSample(float currentSample);
}
