package com.selfi.utils;

public interface IConstants {

	static final String URL = "https://api.flickr.com/services/rest";
	static final String METHOD_RECENT = "flickr.interestingness.getList";
	static final String METHOD_SEARCH = "flickr.photos.search";
	static final String METHOD_GETINFO = "flickr.photos.getInfo";
	static final String METHOD_COMMENTS = "flickr.photos.comments.getList";
	static final String KEY = "77a30ff3e5b6d3ac7075a11ad8c35cdc";
	static final String SECRET = "2ad7071bcfd6b406";
	static final String FORMAT = "json&nojsoncallback=1";
	static final String SORT = "interestingness-desc";
	static final String PER_PAGE = "interestingness-desc";
	static final int NO_OF_ITEMS_PER_PAGE = 10;
	static final String PHOTO_URL = "https://www.flickr.com/photos";
}
