package co.uk.bodyloss.soundchains;

public class Generators {

	
	public static Generator Sine(final int hz) {
		return new Generator() {
			public float getNextSample(float currentSample) {
				return (float) Math.sin((2 * Math.PI * hz * currentSample) / sampleRate);
			}
		};
	}
	
	public static Generator Square(final int hz) {
		return new Generator() {
			public float getNextSample(float currentSample) {
				return Math.sin((2 * Math.PI * hz * currentSample) / sampleRate) > 0 ? 1.0f : -1.0f;
			}
		};
	}
	
	public static Generator Triangle(final int hz) { 
		return new Generator() {
			public float getNextSample(float currentSample) {
				float t = ((hz * currentSample) / sampleRate);
				return (float) Math.abs(t - Math.floor(t)) - 1.0f;
			}
		};
	}
	
	public static Generator Sawtooth(final int hz) {
		return new Generator() {			
			public float getNextSample(float currentSample) {
				float t =  ((hz * currentSample) / sampleRate);
				return (float) (t - Math.floor(t));
			}
		};
	}
	
}
