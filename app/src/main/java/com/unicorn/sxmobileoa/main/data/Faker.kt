package com.unicorn.sxmobileoa.main.data

import com.unicorn.sxmobileoa.main.model.MainItem
import com.unicorn.sxmobileoa.main.model.MainSection
import io.reactivex.Maybe

class Faker {

    fun getMainSection(): Maybe<List<MainSection>> {

        val list = ArrayList<MainSection>()
        list.add(MainSection(header = "公文管理", isHeader = true))
        list.add(MainSection(MainItem(text = "发文")))
        list.add(MainSection(MainItem(text = "收文")))
        list.add(MainSection(MainItem(text = "公文管理3")))

        list.add(MainSection(header = "督查联络", isHeader = true))
        list.add(MainSection(MainItem(text = "督查联络1")))
        list.add(MainSection(MainItem(text = "督查联络2")))
        list.add(MainSection(MainItem(text = "督查联络3")))
        list.add(MainSection(MainItem(text = "督查联络4")))


        return Maybe.just(list)
    }

}