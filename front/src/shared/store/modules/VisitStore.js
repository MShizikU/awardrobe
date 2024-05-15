import type AppStore from "../AppStore";
import {makeAutoObservable} from "mobx";
import $api from "../../../http";
import {message} from "antd";


export default class VisitStore{
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

    getVisitData = async (id) => {
        try {
            this.isLoadingState = true;
            const response = await $api.get('/visit/' + id);
            return response.data;
        } catch (e) {
            this.rootStore.httpError(e);
        } finally {
            this.isLoadingState = false;
        }
    }

    getVisitByFilter = async (id, user_id, cell_id) => {
        try {
            this.isLoadingState = true;
            const response = await $api.post('/visits/filter',{
                id : id,
                user_id: user_id,
                cell_id: cell_id
            } );
            return response.data;
        } catch (e) {
            this.rootStore.httpError(e);
        } finally {
            this.isLoadingState = false;
        }
    }

    getAllVisits = async () => {
        try {
            this.isLoadingState = true;
            const response = await $api.get('/visits');
            return response.data;
        } catch (e) {
            this.rootStore.httpError(e);
        } finally {
            this.isLoadingState = false;
        }
    }
}