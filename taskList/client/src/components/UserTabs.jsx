import React from 'react';
import { useNavigate } from 'react-router-dom';

export const UserTabs = (props) => {
    const { user } = props;
    const nav = useNavigate();
    return (
        <div>
            <button onClick={() => nav(`/user/${user.id}`)}>{user.name}</button>
        </div>
    );
};