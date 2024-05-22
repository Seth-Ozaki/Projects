import { useState } from "react";
import { useNavigate, useOutletContext } from "react-router-dom";
import { create } from "../services/userService";

export const CreateUser = () => {
    const nav = useNavigate();
    const { addUser } = useOutletContext();
    const [userState, setUserState] = useState({
        firstName: '',
        task: [],
        completed: []
    });

    const handleChange = (e) => {
        let newValue;
        if (e.target.type === "checkbox") {
            newValue = e.target.checked;
        } else {
            newValue = e.target.value;
        }
        setUserState((prevState) => ({
            ...prevState,
            [e.target.name]: newValue
        }));
    };

    const [errors, setErrors] = useState({});

    const createUser = (e) => {
        e.preventDefault();

        const newUser = { ...userState };

        create(newUser)
            .then((res) => {
                addUser(res);
                nav("/");
            })
            .catch((err) => {
                setErrors(err.response.data.errors);
            });

    };


    return (
        <>
            <div>
                <div style={{ display: "flex", justifyContent: "center" }}>
                    <form onSubmit={createUser}>
                        <div>
                            <h3>Create a user</h3>
                            <div>
                                <p>First Name:</p>
                                {errors.firstName === undefined ?
                                    <input type="text" value={userState.firstName} name="firstName" onChange={handleChange} />
                                    : <>
                                        <input style={{ border: "2px solid red", background: "transparent" }} type="text" value={userState.firstName} name="firstName" onChange={handleChange} />
                                        {errors.firstName && <p style={{ color: "red" }}>{errors.firstName.message}</p>}
                                    </>
                                }
                            </div>

                            <div>
                                <p>Add a task to start! (Optional)</p>
                                <input type="text" value={userState.task} name="task" onChange={handleChange} />
                            </div>
                        </div>

                        <button style={{ marginTop: "10px" }}>Submit</button>
                    </form>
                </div >
            </div >
        </>
    );
};
