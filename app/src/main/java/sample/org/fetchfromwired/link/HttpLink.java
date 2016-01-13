package sample.org.fetchfromwired.link;


public class HttpLink {

	private static final String HTTP_LINK ;
	private static final String LAST_ARTICLES_LINK ;



	static {
		HTTP_LINK ="http://www.wired.com/wp-json/wp/v2";
		LAST_ARTICLES_LINK = "posts";

	}

	public static String getLastArticlesLink() {
		return LAST_ARTICLES_LINK;
	}

	public static String getHttpLink() {
		return HTTP_LINK;
	}
}
