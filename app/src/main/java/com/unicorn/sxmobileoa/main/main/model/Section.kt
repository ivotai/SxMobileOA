package com.unicorn.sxmobileoa.main.main.model

import com.chad.library.adapter.base.entity.SectionEntity

class Section(t: MainItem? = null, header: String ="", isHeader: Boolean = false) : SectionEntity<MainItem>(t) {

    init {
        this.header = header
        this.isHeader = isHeader
    }

}