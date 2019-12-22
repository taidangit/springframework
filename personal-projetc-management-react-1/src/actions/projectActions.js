import axios from 'axios';
import * as types from './types';

export const createProject = (project, history) => async dispatch => {
    try {
        await axios.post("http://localhost:8084/api/project", project);
        history.push("/dashboard");
        dispatch({
            type: types.GET_ERRORS,
            payload: {}
        });
    } catch(err) {
        dispatch({
            type: types.GET_ERRORS,
            payload: err.response.data
        });
    }
};

export const getProjects = () =>  async dispatch => {
    const res = await axios.get("http://localhost:8084/api/project/list");
    dispatch({
        type: types.GET_PROJECTS,
        payload: res.data
    });
};

export const getProject = (id, history) =>  async dispatch => {
    try {
        const res = await axios.get(`http://localhost:8084/api/project/${id}`);
        dispatch({
            type: types.GET_PROJECT,
            payload: res.data
        });
    } catch(err) {
        history.push("/dashboard");
    }
};

export const deleteProject = (id) =>  async dispatch => {
    await axios.delete(`http://localhost:8084/api/project/${id}`);

    dispatch({
        type: types.DELETE_PROJECT,
        payload: id
    });
};
