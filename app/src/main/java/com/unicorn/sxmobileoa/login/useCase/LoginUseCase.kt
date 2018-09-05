package com.unicorn.sxmobileoa.login.useCase

import com.unicorn.sxmobileoa.app.Global
import com.unicorn.sxmobileoa.app.di.ComponentHolder
import com.unicorn.sxmobileoa.login.BaseUseCase
import com.unicorn.sxmobileoa.login.model.LoginInfo
import com.unicorn.sxmobileoa.login.parse.Response


class LoginUseCase(private val username: String, private val password: String) : BaseUseCase<LoginInfo>() {

    override val busiCode = "oalogin"

    override fun addParameters() {
        addParameter("userName", username)
        addParameter("passWord", password)
    }

    override fun map(response: Response): SimpleResponse<LoginInfo> {
        val simpleResponse = SimpleResponse<LoginInfo>(response.code, response.msg)
        if (response.parameters != null) {
            response.parameters.parameters.forEach {
                if (it != null) {
                    if (it.name == "key") {
                        Global.ticket = it.text
                    }
                    if (it.name == "message") {
                        simpleResponse.message = it.text
                    }
                    if (it.name == "result") {
                        val gson = ComponentHolder.appComponent.getGson()
                        simpleResponse.result = gson.fromJson(it.text, LoginInfo::class.java)
                    }
                }
            }
        }
        return simpleResponse
    }
}