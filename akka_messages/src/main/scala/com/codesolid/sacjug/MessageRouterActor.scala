package com.codesolid.sacjug

import akka.actor.Actor

/**
 * Created by john on 11/10/14.
 */
class MessageRouterActor extends Actor {

    def receive : Receive = {

        case msg: ExamMessage => handleExamMessage(msg)
        case msg: ScheduleMessage => handleScheduleMessage(msg)

        case "Hey" => sender ! "Hay is for horses"
        case _ => sender ! "Unknown message type"

    }

    def handleExamMessage(msg: ExamMessage): Unit = {
        sender ! "Exam Message"
    }


    def handleScheduleMessage(msg: Message): Unit = {
        sender ! ("Schedule Message " + msg.content)
    }
}
