<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<style>
.bigPit {
	height: 256px;
	width: 128px;
	display: block;
}

.player1 {
	border: 2px solid green;
}

.player2 {
	border: 2px solid red;
}
</style>
<body>
	<h1>Bol.com Game</h1>
	<br />
	<div style="text-align: center;">
		<a href="<c:url value="/newGame" />">New Game</a>
	</div>
	<br />

	<table style="margin: 0px auto;">
		<tr>
			<c:set var="stonesPerPit" value="${board.player1.bigPit.stones}"
				scope="page"></c:set>
			<c:if test="${stonesPerPit>21}">
				<c:set var="stonesPerPit" value="21" />
			</c:if>
			<td class="player1" rowspan=2>Player 1<img class="bigPit"
				src="<c:url value="/images/${stonesPerPit}.jpg"/>" />${board.player1.bigPit.stones}
			</td>
			<c:forEach begin="1" end="${board.numberOfPits}" var="i" step="1">
				<c:set var="j" value="${board.numberOfPits-i+1}" scope="page"></c:set>
				<c:set var="stonesPerPit" value="${board.pits[j-1].stones}"
					scope="page"></c:set>
				<c:if test="${stonesPerPit>21}">
					<c:set var="stonesPerPit" value="21" />
				</c:if>
				<td class="player1"><a href="<c:url value="/play/move/${j}" />"><img
						src="/images/${stonesPerPit}.jpg" /></a>${board.pits[j-1].stones}</td>
			</c:forEach>

			<c:set var="stonesPerPit" value="${board.player2.bigPit.stones}"
				scope="page"></c:set>
			<c:if test="${stonesPerPit>21}">
				<c:set var="stonesPerPit" value="21" />
			</c:if>
			<td rowspan=2 class="player2">Player 2<img class="bigPit"
				src="<c:url value="/images/${stonesPerPit}.jpg"/>" />${board.player2.bigPit.stones}
			</td>
		</tr>

		<tr>
			<c:forEach begin="1" end="${board.numberOfPits}" var="i" step="1">
				<c:set var="j" value="${board.numberOfPits+1+i}" scope="page"></c:set>
				<c:set var="stonesPerPit" value="${board.pits[j-1].stones}"
					scope="page"></c:set>
				<c:if test="${stonesPerPit>21}">
					<c:set var="stonesPerPit" value="21" />
				</c:if>
				<td class="player2"><a href="<c:url value="/play/move/${j}" />"><img
						src="/images/${stonesPerPit}.jpg" /></a>${board.pits[j-1].stones}</td>
			</c:forEach>

		</tr>
	</table>

	<div style="text-align: center;">
		<h2>${board.currentPlayer} turn!</h2>


		<div class="messages">
			<c:forEach var="message" items="${board.messages}">
				<p>${message}</p>
			</c:forEach>
		</div>
	</div>

</body>
</html>