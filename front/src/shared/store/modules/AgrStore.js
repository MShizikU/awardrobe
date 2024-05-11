import type AppStore from "../AppStore";
import {makeAutoObservable} from "mobx";
import $api from "../../../http";
import {message} from "antd";


export default class AgrStore{
    rootStore: AppStore;
    isLoading = false;

    constructor(rootStore: AppStore) {
        makeAutoObservable(this);
        this.rootStore = rootStore;
    }

    get isLoadingState() {
        return this.isLoading;
    }

    set isLoadingState(state) {
        this.isLoading = state;
    }

    createAgr = async (status, open_time, close_time, executor_id, branch_id) => {
        try {
            this.isLoadingState = true;
            const response = await $api.post('/agr', {
                status: status,
                open_time: open_time,
                close_time: close_time,
                executor_id: executor_id,
                branch_id: branch_id
            });
            return response.data;
        } catch (e) {
            this.rootStore.httpError(e);
        } finally {
            this.isLoadingState = false;
        }
    }

    getFullAgr = async (id) => {
        try {
            this.isLoadingState = true;
            const response = $api.get('/agr/' + id);
            return response.data;
        } catch (e) {
            this.rootStore.httpError(e);
        } finally {
            this.isLoadingState = false;
        }
    }

    getAgrByFilter = async (id, status, open_time, close_time, executor_id, branch_id) => {
        try {
            this.isLoadingState = true;
            const response = $api.post('/agrs/filter', {
                id: id,
                status: status,
                open_time: open_time,
                close_time: close_time,
                executor_id: executor_id,
                branch_id: branch_id
            });
            return response.data;
        } catch (e) {
            this.rootStore.httpError(e);
        } finally {
            this.isLoadingState = false;
        }
    }

    getAllAgrs = async () => {
        try {
            this.isLoadingState = true;
            const response = $api.get('/agrs');
            return response.data;
        } catch (e) {
            this.rootStore.httpError(e);
        } finally {
            this.isLoadingState = false;
        }
    }

    updateAgr = async (id, status, open_time, close_time, executor_id, branch_id) => {
        try {
            this.isLoadingState = true;
            const response = $api.put('/agr/' + id, {
                status: status,
                open_time: open_time,
                close_time: close_time,
                executor_id: executor_id,
                branch_id: branch_id
            })
            return response.data;
        } catch (e) {
            this.rootStore.httpError(e);
        } finally {
            this.isLoadingState = false;
        }
    }

    deleteAgr = async (id) => {
        try {
            this.isLoadingState = true;
            const response = $api.delete("/agr/" + id)
            return response.data;
        } catch (e) {
            this.rootStore.httpError(e);
        } finally {
            this.isLoadingState = false;
        }
    }


}