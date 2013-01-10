package co.uk.bodyloss.soundchains;

public interface Generator {
	float getNextSample(long currentSample, int sampleRate);
}
