import { useState, useEffect } from "react";
import { useParams, useNavigate, useOutletContext } from "react-router-dom";
import { getOne } from "../services/userService";

export const UserTasks = (props) => {
    const [user, setUser] = useState({});
    const { id } = useParams();
    const nav = useNavigate();
    const [choice, setChoice] = useState('');
    let list = [];
    if (choice === 'task') {
        list = user.task;
    }
    else if (choice === 'completed') {
        list = user.completed;
    }
    useEffect(() => {
        setChoice('');
        getOne(id)
            .then((res) => {
                setUser(res);
            })
            .catch((err) => console.log(err));
    }, [id]);


    return (
        <>
            <div style={{ display: "flex", justifyContent: "space-between" }}>
                <div style={{ display: "flex", gap: "10px" }}>
                    <button onClick={() => setChoice('task')}>To-Do</button>
                    <button onClick={() => setChoice('completed')}>Completed</button>
                </div>
                <div>
                    <button>Create Task</button>
                </div>
            </div>
            <div>
                {
                    list.map((item, idx) => {
                        return <p key={idx}>{item}</p>;
                    })
                }
            </div>
        </>
    );
};


