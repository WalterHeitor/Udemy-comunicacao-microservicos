import {Sequelize} from "sequelize";

const sequelize = new Sequelize("auth_db", "auth_user", "auth_password", {
    host: "localhost",
    dialect: "postgres",
    quoteIdentifiers: false, 
    define: {
        syncOnAssociation: true,
        timestamps: false,
        underscored: true,
        underscoredAll: true,
        freezeTableName: true,
    },
});

sequelize.authenticate().then(() => {
    console.info("Conection has been stablished!")
})
.catch((err) => {
    console.error("Unable to conect to th database.");
    console.error(err.message);
})

export default sequelize;
