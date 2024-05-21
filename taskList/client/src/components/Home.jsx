import { useState } from "react";
import { UserTabs } from "./UserTabs";



export const Home = () => {
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



    return (
        <>
            <div style={{ display: "flex", justifyContent: "space-between" }}>
                <div style={{ display: "flex", gap: "10px", marginBottom: "10px" }}>
                    {
                        users.map((user, idx) => {
                            return <UserTabs user={user} key={idx} />;;
                        })
                    }
                </div>
                <div>
                    <button>Create User</button>
                </div>
            </div>
        </>
    );
};
