package com.example.interchallenge.data.service

import com.example.interchallenge.data.model.PullRequestResponse
import com.example.interchallenge.data.model.SearchResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface AppService {

    @GET("search/repositories")
    suspend fun getJavaRepositories(
        @Query("q") query: String,
        @Query("sort") sort: String,
        @Query("per_page") pageSize: Int,
        @Query("page") page: Int,
    ): SearchResponse

    @GET("repos/{owner}/{repo}/pulls")
    suspend fun getPullRequestsFromRepo(
        @Path("owner") owner: String,
        @Path("repo") repo: String,
        @Query("state") state: String
    ): List<PullRequestResponse>
}