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

    createBranch = async (status, name, manager_id, company_id) => await this.rootStore.performRequest($api.post('/branch', {
        status: status,
        name: name,
        company_id: company_id,
        manager_id: manager_id,
    }));

    getFullBranch = async (id) => await this.rootStore.performRequest($api.get('/branch/' + id));

    getBranchByFilter = async (id, status, name, manager_id, company_id) => await this.rootStore.performRequest($api.post('/branches/filter', {
        id: id,
        status: status,
        name: name,
        company_id: company_id,
        manager_id: manager_id
    }));

    getAllBranches = async () => await this.rootStore.performRequest($api.get('/branches'));

    updateBranch = async (id, status, name, company_id, manager_id) => await this.rootStore.performRequest($api.put('/branch/' + id, {
        status: status,
        name: name,
        company_id: company_id,
        manager_id: manager_id
    }));

    deleteBranch = async (id) => await this.rootStore.performRequest($api.delete("/branch/" + id));


}