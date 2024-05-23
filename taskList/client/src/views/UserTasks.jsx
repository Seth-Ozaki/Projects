import { useState, useEffect } from "react";
import { useParams, useNavigate, useOutletContext } from "react-router-dom";
import { getOne } from "../services/userService";
import { CreateTask } from "../components/CreateTask";

export const UserTasks = (props) => {
    const [user, setUser] = useState({});
    const { id } = useParams();
    const nav = useNavigate();
    const [choice, setChoice] = useState('');
    const [form, setForm] = useState(false);



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

    const resetForm = (task) => {
        setForm(false);
        addNewTask(task);
    };

    const addNewTask = (task) => {
        return setUser({ ...user, task: user.task.concat(task) });
    };


    return (
        <>
            <div style={{ display: "flex", justifyContent: "space-between" }}>
                <div style={{ display: "flex", gap: "10px" }}>
                    <button onClick={() => setChoice('task')}>To-Do</button>
                    <button onClick={() => setChoice('completed')}>Completed</button>
                </div>
                <div>
                    <button onClick={() => setForm(true)}>Create Task</button>
                </div>
            </div>
            <div>
                {
                    list.map((item, idx) => {
                        return <li className='listItem' key={idx}>{item}</li>;
                    })
                }
            </div>
            <div>
                {
                    form &&
                    <CreateTask resetForm={resetForm} />
                }
            </div>
        </>
    );
};


