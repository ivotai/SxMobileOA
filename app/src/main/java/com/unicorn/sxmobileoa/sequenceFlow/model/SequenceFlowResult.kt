package com.unicorn.sxmobileoa.sequenceFlow.model

import com.unicorn.sxmobileoa.select.deptUser.model.User

class SequenceFlowResult(val sequenceFlow: NextTaskSequenceFlow,val userList: List<User>)