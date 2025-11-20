from fastapi import FastAPI
from app.routers import auth, users, posts

app = FastAPI(title="CampusLink API")

app.include_router(auth.router)
app.include_router(users.router)
app.include_router(posts.router)

@app.get("/")
def root():
    return {"message": "CampusLink Backend Running"}
