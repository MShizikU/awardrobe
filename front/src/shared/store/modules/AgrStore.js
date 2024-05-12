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

    createAgr = async (status, open_time, close_time, executor_id, branch_id) => await this.rootStore.performRequest($api.post('/agr', {
        status: status,
        open_time: open_time,
        close_time: close_time,
        executor_id: executor_id,
        branch_id: branch_id
    }));

    getFullAgr = async (id) => await this.rootStore.performRequest($api.get('/agr/' + id));

    getAgrByFilter = async (id, status, open_time, close_time, executor_id, branch_id) => await this.rootStore.performRequest($api.post('/agrs/filter', {
        id: id,
        status: status,
        open_time: open_time,
        close_time: close_time,
        executor_id: executor_id,
        branch_id: branch_id
    }));

    getAllAgrs = async () => await this.rootStore.performRequest($api.get('/agrs'));

    updateAgr = async (id, status, open_time, close_time, executor_id, branch_id) => await this.rootStore.performRequest($api.put('/agr/' + id, {
        status: status,
        open_time: open_time,
        close_time: close_time,
        executor_id: executor_id,
        branch_id: branch_id
    }));

    deleteAgr = async (id) => await this.rootStore.performRequest($api.delete("/agr/" + id));


}