package uk.co.bodyloss.soundchains;

public interface Generator {
	float getNextSample(long currentSample, int sampleRate);
}
