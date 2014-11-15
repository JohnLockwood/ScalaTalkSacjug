package com.codesolid.sacjug

/**
 * Created by john on 11/10/14.
 */

abstract class Message {
    def attributes: Map[String, String]
    def content: String
}

case class ExamMessage (attributes: Map[String, String], content: String) extends Message
case class ScheduleMessage (attributes: Map[String, String], content: String) extends Message
