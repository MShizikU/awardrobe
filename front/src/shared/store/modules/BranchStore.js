import type AppStore from "../AppStore";
import {makeAutoObservable} from "mobx";
import $api from "../../../http";
import {message} from "antd";


export default class BranchStore{
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

    createBranch = async (status, name, manager_id, company_id) => {
        try {
            this.isLoadingState = true;
            const response = await $api.post('/branch', {
                status: status,
                name: name,
                company_id: company_id,
                manager_id: manager_id,
            });
            return response.data;
        } catch (e) {
            this.rootStore.httpError(e);
        } finally {
            this.isLoadingState = false;
        }
    }

    getFullBranch = async (id) => {
        try {
            this.isLoadingState = true;
            const response = $api.get('/branch/' + id);
            return response.data;
        } catch (e) {
            this.rootStore.httpError(e);
        } finally {
            this.isLoadingState = false;
        }
    }

    getBranchByFilter = async (id, status, name, manager_id, company_id) => {
        try {
            this.isLoadingState = true;
            const response = $api.post('/branches/filter', {
                id: id,
                status: status,
                name: name,
                company_id: company_id,
                manager_id: manager_id
            });
            return response.data;
        } catch (e) {
            this.rootStore.httpError(e);
        } finally {
            this.isLoadingState = false;
        }
    }

    getAllBranches = async () => {
        try {
            this.isLoadingState = true;
            const response = $api.get('/branches');
            return response.data;
        } catch (e) {
            this.rootStore.httpError(e);
        } finally {
            this.isLoadingState = false;
        }
    }

    updateBranch = async (id, status, name, company_id, manager_id) => {
        try {
            this.isLoadingState = true;
            const response = $api.put('/branch/' + id, {
                status: status,
                name: name,
                company_id: company_id,
                manager_id: manager_id
            })
            return response.data;
        } catch (e) {
            this.rootStore.httpError(e);
        } finally {
            this.isLoadingState = false;
        }
    }

    deleteBranch = async (id) => {
        try {
            this.isLoadingState = true;
            const response = $api.delete("/branch/" + id)
            return response.data;
        } catch (e) {
            this.rootStore.httpError(e);
        } finally {
            this.isLoadingState = false;
        }
    }


}