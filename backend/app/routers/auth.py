from fastapi import APIRouter, Depends
from sqlalchemy.ext.asyncio import AsyncSession
from app.db.database import get_db
from app.schemas.user import UserCreate, UserLogin
from app.core.security import create_token, verify_password, hash_password
from app.db.models import User

router = APIRouter(prefix="/auth", tags=["Auth"])

@router.post("/register")
async def register(user: UserCreate, db: AsyncSession = Depends(get_db)):
    new_user = User(
        email=user.email,
        password=hash_password(user.password),
        nickname=user.nickname
    )
    db.add(new_user)
    await db.commit()
    return {"msg": "Register success"}

@router.post("/login")
async def login(data: UserLogin, db: AsyncSession = Depends(get_db)):
    result = await db.execute(
        f"SELECT * FROM users WHERE email='{data.email}'"
    )
    user = result.fetchone()
    if not user or not verify_password(data.password, user.password):
        return {"error": "Invalid credentials"}
    token = create_token(user.id)
    return {"token": token}
