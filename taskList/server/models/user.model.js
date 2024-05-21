import { model, Schema } from "mongoose";

const UserSchema = new Schema(
    {
        firstName: {
            type: String,
            required: [true, "You must have a name."],
            minlength: [1, "Minimum 1 character."],
            maxlength: [15, "Maximum 15 characters."]
        },
        task: [String],
        completed: [String]
    },
    { timestamps: true }
);

const User = model("User", UserSchema);
export default User;