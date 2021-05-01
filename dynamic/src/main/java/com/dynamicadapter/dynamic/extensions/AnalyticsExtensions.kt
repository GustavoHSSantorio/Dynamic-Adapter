package com.dynamicadapter.dynamic.extensions

import br.com.xpinc.mobile.commons.core.analytics.IAnalytics
import br.com.xpinc.mobile.commons.core.extensions.putStringMap
import br.com.xpinc.mobile.commons.dynamic.data.model.AnalyticsUserInteraction

fun IAnalytics.sendUserInteraction(userInteraction: AnalyticsUserInteraction?) {
    userInteraction?.let {
        logCustomEvent(it.eventName) {
            it.parameters?.let { parameters -> putStringMap(parameters) }
        }
    }
}