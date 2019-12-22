import axios from 'axios';
import jwt_decode from 'jwt-decode';

import * as types from './types';
import { setJWTToken } from '../securityUltis/setJWTToken';

export const createUser = (user, history) => async dispatch => {
    try {
        await axios.post("http://localhost:8084/api/users/register", user);
        history.push("/login");
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

export const login = (loginRequest, history) => async dispatch => {
    try {
        const res = await axios.post("http://localhost:8084/api/users/login", loginRequest);
        const {token} = res.data;

        localStorage.setItem("jwtToken", token);
        setJWTToken(token);

        const decoded = jwt_decode(token);
        dispatch({
            type: types.SET_CURRENT_USER,
            payload: decoded
        });
        
        dispatch({
            type: types.GET_ERRORS,
            payload: {}
        });

        history.push("/dashboard");

    } catch(err) {
        dispatch({
            type: types.GET_ERRORS,
            payload: err.response.data
        });
    }
};

export const logout = (history) => async dispatch => {
    try {
        localStorage.removeItem("jwtToken");
        setJWTToken("");
        dispatch({
            type: types.LOGOUT,
            payload: {}
        });

    } catch(err) {
        
    }
   
};

export const getUserInfo = () => async dispatch => {
    const res = await axios.get("http://localhost:8084/api/users");
    dispatch({
        type: types.GET_USER_INFO,
        payload: res.data
    });
};

export const updateUserInfo = (user, history) => async dispatch => {
    try {
        await axios.put("http://localhost:8084/api/users/updateUserInfo", user);
        dispatch({
            type: types.GET_ERRORS,
            payload: {}
        });

        history.push("/");
    } catch(err) {
        dispatch({
            type: types.GET_ERRORS,
            payload: err.response.data
        });
    }
};
