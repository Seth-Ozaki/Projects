import { useState } from "react";
import { useParams } from "react-router-dom";

export const UserTasks = (props) => {
    const [users, setUsers] = useState([
        {
            id: 1,
            name: "Seth",
            todo: ["create app", "get it to mvp"],
            completed: ["nothing", "nothing x2"]
        },
        {
            id: 2,
            name: "Haley",
            todo: ["create app"],
            completed: ["nothing"]
        }
    ]);
    const { id } = useParams();

    const [list, setList] = useState([]);
    const choice = () => {
        console.log(id);
    };

    return (
        <>
            <div style={{ display: "flex", gap: "10px" }}>
                <button onClick={() => choice()}>To-Do</button>
                <button>Completed</button>
            </div>
        </>
    );
};
