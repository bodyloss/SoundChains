package co.uk.bodyloss.soundchains.links;

import co.uk.bodyloss.soundchains.ChainLink;

public class Decay extends ChainLink {
	private float rate = 0.0F;
	
	public Decay(float rate) {
		this.rate = rate;
	}

	public float processSample(float sample, long currentSample) {
		// TODO Auto-generated method stub
		return 0;
	}

}
