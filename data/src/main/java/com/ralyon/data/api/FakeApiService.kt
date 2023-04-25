package com.ralyon.data.api

import com.ralyon.data.model.Beer

class FakeApiService : ApiService {

    val fakeBeers = listOf(
        Beer(name = "Buzz"),
        Beer(name = "Trashy Blonde"),
        Beer(name = "Berliner Weisse"),
        Beer(name = "Pilsen Lager"),
        Beer(name = "Avery Brown Dredge"),
        Beer(name = "Electric India"),
        Beer(name = "AB:12"),
        Beer(name = "Fake Lager"),
        Beer(name = "AB:07")
    )

    override suspend fun getBeers(page: Int?, beerName: String?, malt: String?): List<Beer> {
        val fromIndex = if (page == null || page == 1) 0 else (page - 1) * FAKE_PAGE_SIZE
        val toIndex = fromIndex + 2
        return fakeBeers.subList(fromIndex, toIndex)
    }

    companion object {
        const val FAKE_PAGE_SIZE = 2
    }
}