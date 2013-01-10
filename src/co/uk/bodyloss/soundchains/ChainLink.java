package co.uk.bodyloss.soundchains;

/**
 * A single link in a SoundChain. Responsible for processing
 * a single sample and then returning the transformed value
 * @author jciechanowicz
 *
 */
public abstract class ChainLink {
	/**
	 * Sample rate that this link processes at
	 */
	protected int sampleRate;
	
	/**
	 * Process a single sample
	 * @param sample the sample value
	 * @param currentSample the current sample we are at
	 * @return transformed sample
	 */
	public abstract float processSample(float sample, long sampleNum);
	
	public void instantiate(int sampleRate) {
		this.sampleRate = sampleRate;
	}
}
