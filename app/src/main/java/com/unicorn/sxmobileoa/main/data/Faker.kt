package com.unicorn.sxmobileoa.main.data

import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.main.model.MainItem
import com.unicorn.sxmobileoa.main.model.MainSection
import io.reactivex.Observable

class Faker {

    fun getMainSection(): Observable<MainSection> {
        val list = ArrayList<MainSection>().apply {

            add(MainSection(header = "公文管理", isHeader = true))
            add(MainSection(header = "公文管理", t = MainItem(text = "发文", resId = R.mipmap.bangong_01)))
            add(MainSection(header = "公文管理", t = MainItem(text = "收文", resId = R.mipmap.bangong_02)))
            add(MainSection(header = "公文管理", t = MainItem(text = "传阅公文", resId = R.mipmap.bangong_03)))

            add(MainSection(header = "监督联络", isHeader = true))
            add(MainSection(header = "监督联络", t = MainItem(text = "领导批示", resId = R.mipmap.bangong_05)))
            add(MainSection(header = "监督联络", t = MainItem(text = "院领导批示", resId = R.mipmap.bangong_06)))
            add(MainSection(header = "监督联络", t = MainItem(text = "院长批示", resId = R.mipmap.bangong_07)))
            add(MainSection(header = "监督联络", t = MainItem(text = "其他督办", resId = R.mipmap.bangong_08)))
            add(MainSection(header = "管理3", isHeader = true))
            add(MainSection(header = "管理3",t= MainItem(text = "管理3-1", resId = R.mipmap.bangong_09)))
            add(MainSection(header = "管理3",t= MainItem(text = "管理3-2", resId = R.mipmap.bangong_10)))
            add(MainSection(header = "管理3",t= MainItem(text = "管理3-3", resId = R.mipmap.bangong_11)))
            add(MainSection(header = "管理3",t= MainItem(text = "管理3-4", resId = R.mipmap.bangong_12)))
            add(MainSection(header = "管理4", isHeader = true))
            add(MainSection(header = "管理4", t = MainItem(text = "管理4-1", resId = R.mipmap.bangong_97)))
            add(MainSection(header = "管理4", t = MainItem(text = "管理4-2", resId = R.mipmap.bangong_98)))
            add(MainSection(header = "管理4", t = MainItem(text = "管理4-3", resId = R.mipmap.bangong_98_02)))
            add(MainSection(header = "管理4", t = MainItem(text = "管理4-4", resId = R.mipmap.bangong_98_04)))
            add(MainSection(header = "管理5", isHeader = true))
            add(MainSection(header = "管理5", t = MainItem(text = "管理3-1", resId = R.mipmap.bangong_09)))
            add(MainSection(header = "管理5", t = MainItem(text = "管理3-2", resId = R.mipmap.bangong_10)))
            add(MainSection(header = "管理5", t = MainItem(text = "管理3-3", resId = R.mipmap.bangong_11)))
            add(MainSection(header = "管理5", t = MainItem(text = "管理3-4", resId = R.mipmap.bangong_12)))
            add(MainSection(header = "管理6", isHeader = true))
            add(MainSection(header = "管理6", t = MainItem(text = "管理4-1", resId = R.mipmap.bangong_97)))
            add(MainSection(header = "管理6", t = MainItem(text = "管理4-2", resId = R.mipmap.bangong_98)))
            add(MainSection(header = "管理6", t = MainItem(text = "管理4-3", resId = R.mipmap.bangong_98_02)))
            add(MainSection(header = "管理6", t = MainItem(text = "管理4-4", resId = R.mipmap.bangong_98_04)))
            add(MainSection(header = "管理7", isHeader = true))
            add(MainSection(header = "管理7", t = MainItem(text = "管理3-1", resId = R.mipmap.bangong_09)))
            add(MainSection(header = "管理7", t = MainItem(text = "管理3-2", resId = R.mipmap.bangong_10)))
            add(MainSection(header = "管理7", t = MainItem(text = "管理3-3", resId = R.mipmap.bangong_11)))
            add(MainSection(header = "管理7", t = MainItem(text = "管理3-4", resId = R.mipmap.bangong_12)))
            add(MainSection(header = "管理8", isHeader = true))
            add(MainSection(header = "管理8", t = MainItem(text = "管理4-1", resId = R.mipmap.bangong_97)))
            add(MainSection(header = "管理8", t = MainItem(text = "管理4-2", resId = R.mipmap.bangong_98)))
            add(MainSection(header = "管理8", t = MainItem(text = "管理4-3", resId = R.mipmap.bangong_98_02)))
            add(MainSection(header = "管理8", t = MainItem(text = "管理4-4", resId = R.mipmap.bangong_98_04)))

        }


        return Observable.fromIterable(list)
    }

}