package uk.co.bodyloss.soundchains;

public class Generators {

	
	public static Generator Sine(final int hz, int sampleRate) {
		return new Generator() {
			public float getNextSample(long currentSample, int sampleRate) {
				return (float) Math.sin((2 * Math.PI * currentSample * hz) / sampleRate);
			}
		};
	}
	
	public static Generator Cos(final int hz, int sampleRate) {
		return new Generator() {
			public float getNextSample(long currentSample, int sampleRate) {
				return (float) Math.cos((2 * Math.PI * currentSample * hz) / sampleRate);
			}
		};
	}
	
	public static Generator Tan(final int hz, int sampleRate) {
		return new Generator() {
			public float getNextSample(long currentSample, int sampleRate) {
				return (float) Math.tan((2 * Math.PI * currentSample * hz) / sampleRate);
			}
		};
	}
	
}
