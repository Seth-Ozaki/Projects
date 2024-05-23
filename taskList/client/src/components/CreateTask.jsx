import { useState } from "react";
import { addTaskToUser } from "../services/userService";
import { useNavigate, useParams } from "react-router-dom";


export const CreateTask = (props) => {

    const { id } = useParams();
    const nav = useNavigate;
    const [task, setTask] = useState('');


    const addTask = (e) => {
        e.preventDefault();
        addTaskToUser(id, { task })
            .then(res => {
                props.resetForm(task);
            })
            .catch(err => {

            });

    };



    return (
        <>
            <div>
                <div style={{ display: "flex", justifyContent: "center" }}>
                    <form onSubmit={addTask}>
                        <div>
                            <h3>Create a task</h3>

                            <div>
                                <input className="task" type="text" value={task} name="task" onChange={(e) => setTask(e.target.value)} />
                            </div>
                        </div>
                        <button style={{ marginTop: "10px" }}>Submit</button>
                    </form>
                </div >
            </div >
        </>
    );
};

