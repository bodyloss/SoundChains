package co.uk.bodyloss.soundchains;

import java.util.ArrayList;

/**
 * Allows you to combine multiple generators together
 */
public class MultiGenerator extends Generator{
	private ArrayList<Generator> generators = new ArrayList<Generator>();
	
	/**
	 * Add another generator to be used
	 * @param generator
	 * @return
	 */
	public MultiGenerator add(Generator generator) {
		this.generators.add(generator);
		return this;
	}
	

	@Override
	public float getNextSample(float currentSample) {
		float sample = 0.0f;
		for (int i = 0; i < generators.size(); i++) {
			sample += generators.get(i).getNextSample(currentSample);
		}
		
		return sample;
	}

}
