package com.ahmednts.pentavaluethemoviedb.data.responses;

import com.ahmednts.pentavaluethemoviedb.data.models.PopularMovie;

import java.util.List;

/**
 * Created by AhmedNTS on 6/1/2017.
 */
public class PopularMoviesResponse {
    public int page;
    public int total_pages;
    public int total_results;

    public List<PopularMovie> results;

    @Override
    public String toString() {
        return "PopularMoviesResponse{" +
                "page=" + page +
                ", total_pages=" + total_pages +
                ", total_results=" + total_results +
                ", results=" + results +
                '}';
    }
}
