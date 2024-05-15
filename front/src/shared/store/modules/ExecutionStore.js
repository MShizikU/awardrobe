import type AppStore from "../AppStore";
import {action, computed, makeAutoObservable, observable} from "mobx";
import $api, {API_URL} from "../../../http";
import SockJS from "sockjs-client";
import Stomp from "stompjs";
import params from "sockjs-client/lib/transport/receiver/jsonp";

export default class ExecutionStore {
    rootStore: AppStore;
    stompClient = null;
    isConnected = false;
    userId = null;
    curMessage = null;

    get connected() {
        return this.isConnected;
    }

    set connected(value) {
        this.isConnected = value;
    }

    constructor(rootStore: AppStore) {
        makeAutoObservable(this, {
            curMessage: observable,
            isConnected: observable,
            connected: computed,
            stompClient: false
        }, {
            deep: true
        });
        this.rootStore = rootStore;
    }

    connect = () => {
        if (!this.connected){
            const Stomp = require("stompjs");
            var SockJS = require("sockjs-client");
            SockJS = new SockJS(API_URL + "/ws");
            this.stompClient = Stomp.over(SockJS);
            this.stompClient.connect({}, this.onConnected, this.onError);
        }

    }

    onConnected = () => {
        console.log("connected");
        this.stompClient.subscribe(
            "/user/" + this.rootStore.user.id + "/queue/messages",
            this.onMessageReceived
        );
        this.connected = true;
    }

    sendUserMessage = (agrId, userId, cellId, status) => {
        const message = {
            agrId : agrId,
            userId : userId,
            cellId: cellId,
            status: status
        };

        this.stompClient.send("/app/user", {}, JSON.stringify(message));
    }

    sendExecutorMessage = (agrId, userId, executorId, status, cellId, msg) => {
        const message = {
            agrId : agrId,
            userId : userId,
            executorId : executorId,
            status: status,
            cellId: cellId,
            message : msg
        };

        this.stompClient.send("/app/executor", {}, JSON.stringify(message));
    }

    onMessageReceived = (msg) => {
        const parsed = JSON.parse(msg.body);
        this.userId = parsed.userId;
        this.curMessage = parsed;
        console.log(this.curMessage);
    };

}
