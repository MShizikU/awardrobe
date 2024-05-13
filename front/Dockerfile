FROM node:slim
RUN mkdir -p /app
WORKDIR /app
ADD . .
RUN npm install react-scripts && npm install

EXPOSE 3000
CMD ["npm", "start"]