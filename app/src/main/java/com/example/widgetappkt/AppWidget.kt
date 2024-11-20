package com.example.widgetappkt

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.RemoteViews

/**
 * Implementation of App Widget functionality.
 */
class AppWidget : AppWidgetProvider() {

    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        // There may be multiple widgets active, so update all of them
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }
    }

    override fun onEnabled(context: Context) {
        // Enter relevant functionality for when the first widget is created
    }

    override fun onDisabled(context: Context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

internal fun updateAppWidget(
    context: Context,
    appWidgetManager: AppWidgetManager,
    appWidgetId: Int
) {
    val widgetText = context.getString(R.string.appwidget_text)
    // Construct the RemoteViews object
    val remoteViews = RemoteViews(context.packageName, R.layout.app_widget)

//    setting TextView of .xml file into Widget
    remoteViews.setTextViewText(R.id.appwidget_text, widgetText)

//    Getting Intent Action View in intent variable
    var intent = Intent(Intent.ACTION_VIEW)
//    running new task on intent trigger
    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
//    loading data and Uri on intent trigger
    intent.data = Uri.parse("https://chatgpt.com")

//    getting W idget clicked or Widget child item clicked
    val pendingIntent = PendingIntent.getActivity(context,0,intent, PendingIntent.FLAG_IMMUTABLE)
    //    triggering and running intent on Widget child clicked
    remoteViews.setOnClickPendingIntent(R.id.appwidget_text,pendingIntent)

   // appWidgetManager.updateAppWidget(appWidgetId,remoteViews)

    // Instruct the widget manager to update the widget
    appWidgetManager.updateAppWidget(appWidgetId, remoteViews)
}