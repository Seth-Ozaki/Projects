import axios from "axios";

const http = axios.create({
    baseURL: 'http://localhost:8000/api',
});


const getAll = () => {
    return http.get('/users')
        .then(res => res.data)
        .catch(err => {
            throw err;
        });
};

const getOne = (id) => {
    return http.get(`/users/${id}`)
        .then(res => res.data)
        .catch(err => {
            throw err;
        });
};

const update = (user) => {
    return http.put(`/users/${user._id}`, user)
        .then(res => res.data)
        .catch(err => {
            throw err;
        });
};

const updateTask = (user) => {
    return http.put(`/users/task/${user._id}`, user)
        .then(res => res.data)
        .catch(err => {
            throw err;
        });
};

const deleteThisUser = (user) => {
    return http.delete(`/users/${user}`)
        .then(res => res.data)
        .catch(err => {
            throw err;
        });
};

const create = (user) => {
    return http.post('/users', user)
        .then(res => res.data)
        .catch(err => {
            throw err;
        });
};

export {
    getAll,
    getOne,
    update,
    deleteThisUser,
    create,
    updateTask
};