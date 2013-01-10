package co.uk.bodyloss.soundchains;

/**
 * A single link in a SoundChain. Responsible for processing
 * a single sample and then returning the transformed value
 * @author jciechanowicz
 *
 */
public interface ChainLink {
	/**
	 * Process a single sample
	 * @param sample the sample value
	 * @param currentSample the current sample we are at
	 * @return transformed sample
	 */
	float processSample(float sample, long currentSample);		
}
