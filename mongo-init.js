db.createUser(
    {
        user: "root",
        pwd: "root",
        roles: [
            {
                role: "readWrite",
                db: "desafio_tds"
            }
        ]
    }
);
