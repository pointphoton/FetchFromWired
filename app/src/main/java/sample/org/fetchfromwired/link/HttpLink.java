package sample.org.fetchfromwired.link;


public class HttpLink {

	private static final String WIRED_LINK ;
	private static final String YANDEX_LINK ;
	private static final String YANDEX_EN_TR;
	private static final String LAST_ARTICLES_LINK ;



	static {
		WIRED_LINK ="http://www.wired.com/wp-json/wp/v2";
		YANDEX_LINK="https://translate.yandex.net/api/v1.5/tr.json/translate?key=trnsl.1.1.20160113T174708Z.1bd4260b1f111d23.91c90ecc6b76a0f2774c0f12f7507040db86b685&text=";
		YANDEX_EN_TR="&lang=en-tr";
		LAST_ARTICLES_LINK = "posts";

	}

	public static String getWiredLink() {
		return WIRED_LINK;
	}

	public static String getYandexLink() {
		return YANDEX_LINK;
	}

	public static String getYandexEnTr() {
		return YANDEX_EN_TR;
	}

	public static String getLastArticlesLink() {
		return LAST_ARTICLES_LINK;
	}
}
