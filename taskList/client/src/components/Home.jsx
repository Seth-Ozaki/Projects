import { useState, useEffect } from "react";
import { UserTabs } from "./UserTabs";
import { getAll } from "../services/userService";
import { useNavigate, Outlet } from "react-router-dom";



export const Home = () => {
    const nav = useNavigate();

    const [users, setUsers] = useState([]);

    useEffect(() => {
        getAll()
            .then((res) => {
                setUsers(res);
            })
            .catch(err => console.log(err));
    }, []);

    const addUser = (newUser) => {
        setUsers([...users, newUser]);
    };


    return (
        <>
            <h1>To Do List</h1>
            <div style={{ display: "flex", justifyContent: "space-between" }}>
                <div style={{ display: "flex", gap: "10px", marginBottom: "10px" }}>
                    {
                        users.map((user, idx) => {
                            return <UserTabs user={user} key={idx} />;;
                        })
                    }
                </div>
                <div style={{ display: "flex", gap: "10px", marginBottom: "10px" }}>
                    <button onClick={() => nav('/')}>Home</button>
                    <button onClick={() => nav('/add')}>Create User</button>
                </div>
            </div>
            <Outlet context={{ addUser }} />
        </>
    );
};
