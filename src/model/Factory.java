package model;

public class Factory {
	public static ImageAnalyst generateImageAnalyst() {
		return new ImageAnalyst();
	};

	public static TextMiner generateTextMiner() {
		return new TextMiner();
	};

	public static InstagramFetcher generateInstsgramFetcher() {
		return new InstagramFetcher();
	};
}
